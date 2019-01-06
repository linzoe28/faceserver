<%-- 
    Document   : client
    Created on : 2018/10/11, 下午 04:16:51
    Author     : imsofa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script
            src="https://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
    </head>
    <body>
        <script>
            $(document).ready(function(){
                $.ajax("http://imsofa.rocks:8080/FaceResultServer/FaceemotionService",{
                    type: "POST",
                    data: {
                        base64: "",
                        id: "",
                        emotion: "",
                        json: JSON.stringify({"status":"success","landmarks":{"EyeRightOuter":[289,110],"MouthLeft":[222,179],"EyeLeftInner":[227,110],"EyebrowRightOuter":[303,97],"EyeRightInner":[263,111],"EyebrowLeftInner":[235,92],"NoseRightAlarOutTip":[256,156],"EyebrowRightInner":[254,92],"NoseLeftAlarOutTip":[226,154],"MouthRight":[264,181],"EyebrowLeftOuter":[194,92],"EyeLeftOuter":[203,108]},"largestFaceBoundingBox":{"width":130,"top":80,"height":130,"left":180}}),
                        datatime:""+new Date().getTime()
                    }
                });
            });
        </script>
    </body>
</html>
