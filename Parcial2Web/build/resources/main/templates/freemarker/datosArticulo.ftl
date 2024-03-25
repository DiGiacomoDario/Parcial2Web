<!DOCTYPE html>
<html>
<head>
    <title>Información Articulo ${articulo.titulo}</title>
    <link href="/css/miEstilo.css" rel="stylesheet" >
</head>
<body>
<h1>Id Articulo ${articulo.id?string["0"]} -  ${articulo.titulo}</h1>
<h2>Cuerpo ${articulo.cuerpo}</h2>
<h2>Etiquetas</h2>
<ul>
    <#list articulo.listaEtiquetas as etiquetas>
        <li>${etiqueta.etiquetas}</li>
    </#list>
</ul>
</body>
</html>
