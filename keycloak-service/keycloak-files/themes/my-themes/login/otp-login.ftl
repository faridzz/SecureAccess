<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Enter OTP</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        .container {
            max-width: 400px;
            margin: auto;
        }
        label, input {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }
        button {
            padding: 10px;
            width: 100%;
            background-color: #007bff;
            border: none;
            color: white;
            cursor: pointer;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Enter OTP</h1>
        <#if message??>
            <div class="error">${message}</div>
        </#if>
        <form method="post" action="${url.loginAction}">
            <label for="otp">Please enter the OTP sent to your phone:</label>
            <input type="text" id="otp" name="otp" required autofocus>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>

