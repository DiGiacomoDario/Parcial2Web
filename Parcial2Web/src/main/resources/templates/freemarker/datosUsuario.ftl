<!DOCTYPE html>
<html>
<head>
    <title>Información Usuario ${usuario.nombre}</title>
    <link href="/css/miEstilo.css" rel="stylesheet" >
</head>
<body>
<h1>Nombre: ${usuario.nombre}</h1>
<h2>Username: ${usuario.username}</h2>
<h2>Contraseña: ${usuario.password}</h2>
<h2>Administrador: ${usuario.admintrator?string("Si", "No")}</h2>
<h2>usuario: ${usuario.usuario?string("Si", "No")}</h2>
</body>
</html>
