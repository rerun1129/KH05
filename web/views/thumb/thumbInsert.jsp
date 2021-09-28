<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사진 게시판 글쓰기</title>
    <script src="/myWeb/resources/js/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/myWeb/resources/css/header.css"/>
    <style>
        section {
            width: 800px;
            height: 700px;
            background: black;
            color: white;
            margin-left: auto;
            margin-right: auto;
            margin-top: 50px;
            padding: 30px;
        }

        table {
            border: 1px solid white;
        }

        #insertArea {
            width: 500px;
            height: 450px;
            margin-left: auto;
            margin-right: auto;
        }

        #titleImgArea {
            width: 350px;
            height: 200px;
            border: 2px dashed grey;
            text-align: center;
            display: table-cell;
            vertical-align: middle;
        }

        [id*=ImgArea]:hover {
            cursor: pointer;
        }

        [id*=contentImgArea] {
            width: 150px;
            height: 100px;
            border: 2px dashed grey;
            text-align: center;
            display: table-cell;
            vertical-align: middle;
        }

        #btnArea {
            margin-top: 15px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }
        .btnArea{
            text-align: center;
        }
    </style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<% if (m != null) { %>
<section>
    <br>
    <h2 align="center">사진 게시글 작성</h2>
    <form action="<%= request.getContextPath() %>/insert.tn"
          method="post" enctype="multipart/form-data">
        <div class="insertArea">
            <!-- 게시글 추가 영역 -->
            <input type="hidden" name="userId" value="<%= m.getUserId() %>"/>
            <table align="center">
                <tr>
                    <td width="100px">제목</td>
                    <td colspan="3">
                        <input type="text" name="title" size="45"/>
                    </td>
                </tr>
                <tr>
                    <td>대표 이미지</td>
                    <td colspan="3">
                        <!-- div#titleImgArea -->
                        <div id="titleImgArea">
                            <img id="titleImg" width="350" height="200"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>내용 사진</td>
                    <!-- (td>div#contentImgArea>img#contentImg[width=120 height=100])*3 -->
                    <td>
                        <div id="contentImgArea1">
                            <img id="contentImg1" width="120" height="100"/>
                        </div>
                    </td>
                    <td>
                        <div id="contentImgArea2">
                            <img id="contentImg2" width="120" height="100"/>
                        </div>
                    </td>
                    <td>
                        <div id="contentImgArea3">
                            <img id="contentImg3" width="120" height="100"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="100px">사진 메모</td>
                    <td colspan="3">
                        <textarea name="content" cols="50" rows="5" style="resize:none;"></textarea>
                    </td>
                </tr>
            </table>

        </div>
        <div class="fileArea" id="fileArea">
            <!-- 첨부할 사진 추가 영역 -->
            <!-- (input:file#thumbnailImg[name=thumbnailImg onchange=loadImg(this, )])*4 -->
            <input type="file" name="thumbnailImg1" id="thumbnailImg1" onchange="loadImg(this,1);" accept="image/*"/>
            <input type="file" name="thumbnailImg2" id="thumbnailImg2" onchange="loadImg(this,2);" accept="image/*"/>
            <input type="file" name="thumbnailImg3" id="thumbnailImg3" onchange="loadImg(this,3);" accept="image/*"/>
            <input type="file" name="thumbnailImg4" id="thumbnailImg4" onchange="loadImg(this,4);" accept="image/*"/>
        </div>
        <div class="btnArea">
            <button type="submit">작성 완료</button>
            <button type="reset">작성 취소</button>
        </div>
    </form>
</section>
<script>
    // 사진 게시글 미리보기 기능 구현
    $(function () {
        $('#titleImgArea').click(function () {
            $('#thumbnailImg1').click();
        });

        $('#contentImgArea1').click(function () {
            $('#thumbnailImg2').click();
        });

        $('#contentImgArea2').click(function () {
            $('#thumbnailImg3').click();
        });

        $('#contentImgArea3').click(function () {
            $('#thumbnailImg4').click();
        });

        $('#fileArea').hide();
    })

    function loadImg(img, num) {
        if (img.files && img.files[0]) {

            var reader = new FileReader();

            reader.onload = function (e) {

                switch (num) {
                    case 1 :
                        $('#titleImg').attr('src', e.target.result);
                        break;
                    case 2 :
                        $('#contentImg1').attr('src', e.target.result);
                        break;
                    case 3 :
                        $('#contentImg2').attr('src', e.target.result);
                        break;
                    case 4 :
                        $('#contentImg3').attr('src', e.target.result);
                        break;
                }
            }

            reader.readAsDataURL(img.files[0]);
        }
    }
</script>
<% } else { // 비회원 접근 차단
    request.setAttribute("exception", new Exception("비회원 접근"));
    request.setAttribute("error-msg", "회원 로그인 후 진행하세요!");

    request.getRequestDispatcher("../common/errorPage.jsp").forward(request, response);
} %>
<%@ include file="../common/footer.jsp" %>
</body>
</html>








