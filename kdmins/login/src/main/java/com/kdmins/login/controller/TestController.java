/*
package com.kdmins.login.controller;

import com.kdmins.common.base.BaseResult;
import com.kdmins.common.websocket.WebSocketServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

*/
/**
 * @author ：lsy
 * @date ：Created in 2020/5/11 15:05
 * @modified By：
 *//*


@RestController
@CrossOrigin
public class TestController {

    @GetMapping("test888")
    public BaseResult test888(@RequestParam("groupId") String groupId,@RequestParam("userId") String userId,@RequestParam("message") String message) throws IOException {


        return BaseResult.success(WebSocketServer.sendInfo(message,userId,groupId));
    }
    @GetMapping("test999")
    public BaseResult test888(@RequestParam("groupId") String groupId,@RequestParam("message") String message) throws IOException {


        return BaseResult.success(WebSocketServer.sendInfo(message,groupId));
    }



}
*/
