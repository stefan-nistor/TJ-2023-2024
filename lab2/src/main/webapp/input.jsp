<!DOCTYPE html>
<html>
<head>
    <title> Java File Upload Servlet </title>
</head>
<body>

<form action="upload" method="post" enctype="multipart/form-data">
    Select file to upload:
    <input type="file" name="file" id="file" accept=".graph,.dimacs,.txt">
    <input type="submit" value="Upload File" name="submit">
</form>

</body>
</html>