package com.kdmins.common.util;
import com.kdmins.common.pojo.file;
import com.kdmins.common.pojo.fileOpResult;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.annotation.Configuration;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Configuration
public class fileUtil {
    private static final int BUFFER_SIZE = 2 * 1024;

    String mianDirectory;

    public String getMianDirectory() {
        return mianDirectory;
    }

    public void setMianDirectory(String mianDirectory) {
        this.mianDirectory = mianDirectory;
    }



  /*获取文件名称*/
    public String getFileName(String fileName){

        return fileName.substring(0,fileName.lastIndexOf("."));
    }
    /*获取文件后缀*/
    public String getFileSuffix(String fileName){

        return fileName.substring(fileName.lastIndexOf(".")+1);
    }


    /*把文件列表打包*/
    public fileOpResult fileListToZip(List<file> srcFiles, String zipPath, String name) throws RuntimeException, IOException {
        FileOutputStream out = new FileOutputStream(new File(zipPath+'/'+name));
        fileOpResult result=new fileOpResult();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            for (file srcFile : srcFiles) {
                File downFile=new File(srcFile.getFilePath());
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getFileName()+"."+srcFile.getFileSuffix()));
                int len;
                FileInputStream in = new FileInputStream(downFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            result.setFlag(true);
            result.setMessage(zipPath+'/'+name);

        } catch (Exception e) {
            result.setFlag(false);
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {





            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            out.close();
        }
        return result;
    }
    /*获取文件编码*/
    public  String codeString(File fileName) throws Exception{
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        IOUtils.closeQuietly(bin);
        return code;
    }
   /*读取文本*/
    public  String readTxt(String  filePath) throws Exception {
        File file=new File(filePath);
        String code=codeString(file);
        BufferedReader bre = null;
        StringBuffer sBuffer = new StringBuffer();
            bre = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), code));//此时获取到的bre就是整个文件的缓存流
            String str;
            while ((str = bre.readLine())!= null) // 判断最后一制行不存在，为空结束循环
            {
                sBuffer.append("\n"+str);
            };


   String test=sBuffer.toString();
  System.out.println(test);


        return test;
    }







    /*递归遍历目录*/
    public  void folderMethod2(String path,List<Map> list,Map<String,Boolean> map) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        folderMethod2(file2.getAbsolutePath(),list,map);
                    } else {
                        System.out.println(map);
                        String realpath=file2.getAbsolutePath().replaceAll("\\\\","/");
                        Boolean flag = map.get(realpath);
                        try{
                            if(flag);
                            {
                                System.out.println(realpath);

                            }
                        }catch (Exception ex){
                            Map aaa=new HashMap();
                            aaa.put("filePath",realpath);
                            aaa.put("fileName",getFileName(file2.getName()));
                            aaa.put("fileSuffix",getFileSuffix(file2.getName()));
                            list.add(aaa);
                        }


                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
    public Boolean directoryIsExist(String path){
        File file=new File(path);

        if(file.exists()){

        }else{
            file.mkdirs();
        }
        return true;
    }
    /*检测文件是否存在*/
    public Boolean checkFileExist(String filePath){
        File file=new File(filePath);

        return file.exists();
    }
    /*删除文件*/
    public fileOpResult deleteFile(String path){
        File file=new File(path);
        if(file.isDirectory()){
            System.out.println(path);
            Boolean flag=deleteDirectory(file);
            return new fileOpResult(flag,"删除成功");
        }else{
            if(file.exists()){
                Boolean flag=file.delete();
                return new fileOpResult(flag,"删除成功");
            }else{
                return new fileOpResult(false,"该文件不存在");
            }
        }
    }

    /*删除文件*/


    public fileOpResult deleteFile(String path,Boolean fileFlag){
        File file=new File(path);
        fileOpResult result=new fileOpResult();
        if(file.exists()){
            if(fileFlag){
                if(file.isFile()){
                    Boolean flag=file.delete();
                    result.setFlag(flag);
                }else{
                    result.setFlag(false);
                    result.setMessage("路径不是文件路径");
                }
            }else{
                if(file.isDirectory()){
                    Boolean flag=deleteDirectory(file);;
                    result.setFlag(flag);
                }else{
                    result.setFlag(false);
                    result.setMessage("路径不是目录路径");
                }
            }




        }else{
            result.setFlag(false);
            result.setMessage("该路径不存在");
        }


        /*if(file.isDirectory()){
            System.out.println(path);
            Boolean flag=deleteDirectory(file);
            return new fileOpResult(flag,"删除成功");
        }else{

        }*/
        return result;
    }



    /*移动文件*/

    public fileOpResult moveFile(String fileOldPath,String fileNewPath) throws IOException {

        File old=new File(fileOldPath);
        if(old.exists()){
            old.renameTo(new File(fileNewPath) );
            return new fileOpResult(true,"移动成功");
        }else{
            return new fileOpResult(false,"该文件不存在");
        }

    }
    /*删除目录*/
    public boolean  deleteDirectory(File dir){
        if (dir.isDirectory()) {
            String[] children = dir.list();

            for (int i=0; i<children.length; i++) {

                boolean success = deleteDirectory(new File(dir, children[i]));

                if (!success) {
                    return false;
                }
            }
        }

      return dir.delete();
    }
    /*创建文件*/
    public fileOpResult createFile(InputStream fileInputIo,FileOutputStream fileOutIo) throws IOException {
        byte fileByte[] = new byte[1024*8];//创建搬运工具
        int len = 0;//创建长度
        while((len = fileInputIo.read(fileByte))!=-1)//循环读取数据
        {
            fileOutIo.write(fileByte,0,len);
        }
        fileInputIo.close();//释放资源
        fileOutIo.close();//释放资源
        System.out.println("流关闭");
         return new fileOpResult(true,"创建成功");
    }
    public fileOpResult createFile(InputStream fileInputIo,String filePath,String fileName,String suffix) throws IOException {
        createFile(fileInputIo,filePath,fileName+"."+suffix);
        return new fileOpResult(true,"创建成功");
    }
    public fileOpResult createFile(InputStream fileInputIo,String directoryPath,String fileAllName) throws IOException {
        FileOutputStream fileOutIo=null;
        directoryIsExist(directoryPath);
        fileOutIo=new FileOutputStream(directoryPath+"/"+fileAllName);
        createFile(fileInputIo,fileOutIo);
        return new fileOpResult(true,"创建成功");
    }
    public fileOpResult createFile(String oldPath,String newPath,String fileName,String suffix) throws IOException {
        if(directoryIsExist(newPath)){
            FileInputStream fileInputIo=new FileInputStream(oldPath+"/"+fileName+"."+suffix);
            FileOutputStream fileOutIo=new FileOutputStream(newPath+"/"+fileName+"."+suffix);
            createFile(fileInputIo,fileOutIo);
        }
        return new fileOpResult(true,"创建成功");
    }
    public fileOpResult copyFile(String oldPath,String newPath,String fileName,String suffix) throws IOException {
       if(directoryIsExist(newPath)){
           FileInputStream fileInputIo=new FileInputStream(oldPath+"\\"+fileName+"."+suffix);
           FileOutputStream fileOutIo=new FileOutputStream(newPath+"\\"+fileName+"."+suffix);
           createFile(fileInputIo,fileOutIo);
       }
        return new fileOpResult(true,"复制成功");
    }
    /*移动加重命名*/
    public fileOpResult moveFile(String oldPath,String newPath,String oldFileName,String newFileName,String suffix) throws IOException {
        return moveFile( oldPath+oldFileName+"."+suffix, newFileName+"."+suffix);

    }


    public fileOpResult moveFile(String oldPath,String newPath,String oldFileFullName,String newFileFullName ) throws IOException {

        String path=oldPath+"/"+oldFileFullName;
        System.out.println(path);
        File old=new File(path);
        String newPath1=newPath+"/"+newFileFullName;
        System.out.println(newPath1);
        if(old.exists() && directoryIsExist(newPath)){


            old.renameTo(new File(newPath1) );
            System.out.println("该文件存在");
            return new fileOpResult(true,"复制成功");
        }else{
            System.out.println("该文件不存在");
            return new fileOpResult(false,"该文件不存在");
        }

    }
    public   fileOpResult rename(String path,String oldFileName,String newFileName,String suffix) throws IOException {

        return moveFile(path,path,oldFileName+"."+suffix,newFileName+"."+suffix);
    }
    public   fileOpResult remove(String oldPath,String newPath,String fileName,String suffix) throws IOException {

        return moveFile(oldPath,newPath,fileName+"."+suffix,fileName+"."+suffix);
    }
    public   fileOpResult remove(Boolean test,String oldPath,String newPath,String fileName,String suffix) throws IOException {
        String newPath1=newPath+"/"+fileName+"."+suffix;
        System.out.println(newPath1);
        System.out.println(oldPath);
        directoryIsExist(newPath);
        return moveFile(oldPath,newPath1);
    }

    public fileOpResult deleteFile(String path, String fileName, String suffix){

        return deleteFile(path+"/"+fileName+"."+suffix);
    }
}
