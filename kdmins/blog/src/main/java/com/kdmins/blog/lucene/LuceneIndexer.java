package com.kdmins.blog.lucene;
import com.kdmins.blog.mapper.BlogMapper;
import com.kdmins.blog.pojo.Artitle;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKAnalyzer;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Component
public class LuceneIndexer {
    public String indexDir ;
    Directory directory;
    @Autowired
    BlogMapper BlogMapper;
    @PostConstruct
    public boolean createIndex() throws IOException{
         if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1){
             indexDir= "C:\\lucene";
         }else{
             indexDir= "/www/server/lucene";
         }
         directory = FSDirectory.open(FileSystems.getDefault().getPath(indexDir));

        extendDic();
        List<Artitle> artitles = BlogMapper.getAllArtitle();
        long startTime = System.currentTimeMillis();//记录索引开始时间
        IKAnalyzer analyzer = new IKAnalyzer(true);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        for(int i = 0; i < artitles.size();i++){
            System.out.println(artitles.get(i).getName());
            Document doc = new Document();
            //添加字段
            doc.add(new StringField("id",  artitles.get(i).getId(), Field.Store.YES)); //添加内容
            doc.add(new TextField("title",artitles.get(i).getName(), Field.Store.YES)); //添加文件名，并把这个字段存到索引文件里
            doc.add(new TextField("content", Jsoup.parse(artitles.get(i).getText()).text() , Field.Store.YES)); //添加文件路径
            indexWriter.addDocument(doc);
        }
      
        indexWriter.commit();
        System.out.println("共索引了"+indexWriter.numRamDocs()+"个文件");
        indexWriter.close();
        System.out.println("创建索引所用时间："+(System.currentTimeMillis()-startTime)+"毫秒");

        return true;
    }
    public  void extendDic(){
        Configuration cfg= DefaultConfig.getInstance();
        Dictionary.initial(cfg);
        Dictionary dic=Dictionary.getSingleton();
        HashSet<String> set=new HashSet<>();
        set.add("静态代理");
        dic.addWords(set);
    }
    public  List search(String keyWord)
    {
        DirectoryReader directoryReader = null;
        try
        {
            directoryReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

            // 4、创建搜索的Query
            IKAnalyzer analyzer = new IKAnalyzer(true);


            // 简单的查询，创建Query表示搜索域为content包含keyWord的文档
            QueryParser querys = new QueryParser("content", analyzer);
            querys.setDefaultOperator(QueryParser .Operator.AND);
            Query query = querys.parse(keyWord);

      /*.parse(keyWord)*/
            /*String[] fields = {"content","name"}; // 要搜索的字段，一般搜索时都不会只搜索一个字段
            // 字段之间的与或非关系，MUST表示and，MUST_NOT表示not，SHOULD表示or，有几个fields就必须有几个clauses
            BooleanClause.Occur[] clauses = {BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD};
            // MultiFieldQueryParser表示多个域解析， 同时可以解析含空格的字符串，如果我们搜索"上海 中国"
            Query multiFieldQuery = MultiFieldQueryParser.parse(keyWord, fields, clauses, analyzer);*/

            // 5、根据searcher搜索并且返回TopDocs
            TopDocs topDocs = indexSearcher.search(query, 100); // 搜索前100条结果
            System.out.println("共找到匹配处：" + topDocs.totalHits); // totalHits和scoreDocs.length的区别还没搞明白
            // 6、根据TopDocs获取ScoreDoc对象
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            System.out.println("共找到匹配文档数：" + scoreDocs.length);

            QueryScorer scorer = new QueryScorer(query, "content");
            // 自定义高亮代码
            SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");
            Highlighter highlighter = new Highlighter(htmlFormatter, scorer);
            highlighter.setTextFragmenter(new SimpleSpanFragmenter(scorer));
            List<Artitle> result=new ArrayList<>();

            for (ScoreDoc scoreDoc : scoreDocs)
            {
                // 7、根据searcher和ScoreDoc对象获取具体的Document对象
                Document document = indexSearcher.doc(scoreDoc.doc);
                Artitle artitle=new Artitle();
                artitle.setId(document.get("id"));
                artitle.setName(document.get("title"));
                artitle.setArtitleShort(highlighter.getBestFragment(analyzer, "content", document.get("content")));
                result.add(artitle);

            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(directoryReader != null) directoryReader.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }



}
