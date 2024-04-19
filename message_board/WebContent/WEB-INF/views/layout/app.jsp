<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>Message board</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Message board Application</h1>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Tomo Ito.
            </div>
        </div>
    </body>
</html>