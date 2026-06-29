<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>超时</title>
</head>
<body>
    <%
        String borrowId = (String) request.getAttribute("borrow_id");
        System.out.println("borrowId: " + borrowId);
    %>
    <h1>超时</h1>
    <p>您的借阅已超时，请及时归还并支付逾期费用。</p>

    <a href="return.do?borrow_id=<%=borrowId%>&pay_overtime=true">支付逾期费用</a>
    <a href="javascript:history.back()">返回上级</a>

</body>
</html>