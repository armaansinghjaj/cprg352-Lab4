<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit note</title>
    </head>
    <body>
        <h1>${notehead}</h1>
        <h2>Edit Note</h2>
        <form method="POST" action="note">
            <label>Title:</label>
            <input type="text" name="title" value="${note.title}">
            <br>
            <label>Contents:</label>
            <textarea rows="6" cols="30" name="contents">${note.contents}</textarea>
            <br>
            <input type="submit" value="Save">
        </form>
    </body>
</html>
