<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Books - test task</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
    <link href="/resources/css/style.css" rel="stylesheet" />

    <script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <script src="/resources/js/scripts.js"></script>
</head>
<body>
    <section class="header">
        <div class="container">
        </div>
    </section>
    <div class="container">
        <@content/>
    </div>
    <footer>
    <div class="container">Books 2018</div>
    </footer>
</body>
</html>