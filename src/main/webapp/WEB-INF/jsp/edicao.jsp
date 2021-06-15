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
                    <title>Atualizar dados</title>
                </head>

                <body>
                    <div>
                        <form:form action="/editar/${pessoa.id}/" method="POST" modelAttribute="pessoadto">
                            <spring:bind path="nome">
                                <div class="${status.error ? 'has-error' : ''}">
                                    <form:input id="inp_nome" type="text" path="nome" placeholder="${pessoa.nome}" />
                                    <form:errors path="nome"></form:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="email">
                                <div class="${status.error ? 'has-error' : ''}">
                                    <form:input id="inp_email" type="text" path="email" placeholder="${pessoa.email}" />
                                    <form:errors path="email"></form:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="telefone">
                                <div class="${status.error ? 'has-error' : ''}">
                                    <form:input id="inp_telefone" type="text" path="telefone"
                                        placeholder="${pessoa.telefone}" />
                                    <form:errors path="telefone"></form:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="cep">
                                <div class="${status.error ? 'has-error' : ''}">
                                    <form:input id="inp_cep" type="text" path="cep"
                                        placeholder="${pessoa.endereco.cep}" />
                                    <form:errors path="cep"></form:errors>
                                </div>
                            </spring:bind>
                            <form:button type="submit">Atualizar</form:button>
                        </form:form>
                        <a href="/pessoas" type="button">Voltar</a>
                    </div>
                </body>

                </html>