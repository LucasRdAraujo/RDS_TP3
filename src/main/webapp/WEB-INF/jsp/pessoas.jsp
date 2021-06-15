<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
            <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
                <!DOCTYPE html>
                <html lang="pt-br">

                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Pessoas</title>
                </head>

                <body>
                    <form action="/pessoas/buscar" method="post">
                        <input type="text" name="email" id="inp_email">
                        <input type="submit" value="Procurar">
                    </form>

                    <table>
                        <c:forEach items="${pessoas}" var="pessoa">
                            <tr>
                                <td><img src="${pessoa.imagemPerfil}" alt=""></td>
                                <td>${pessoa.nome}</td>
                                <td><a href="/editar/${pessoa.id}" type="button">Editar</a></td>
                                <td><a href="/deletar/${pessoa.id}" type="button">Deletar</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="/cadastrar" type="button">Cadastrar outros</a>
                </body>

                </html>