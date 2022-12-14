<head>
    <link rel="stylesheet" href="./escuela.css"/>
    <title>Creando Exámen...</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
</head>

<?php
require_once "./cabecera1.php";
require_once "../Modelos/controladorAdmin.php";
require_once "../conexionBD.php";

$cnx = conexionBD::getConexion();

echo "<br>";
echo "<h2>Nuevo Exámen</h2>";
echo "<div class='crearExam'>
            <form action='' method='post'>
             <label for='Categoria'>Categoría: </label>";
if (isset($_REQUEST['setNR'])) {
    echo "<select id='Categoria' name='categ' value='{$_REQUEST['categ']}'>";
    echo "<option>--SELECCIONE--</option>";
    mostrarCategs($cnx);
    echo "</select>";
    echo "&nbsp;<button class='buton1' type='submit'>+</button>";
} else {
    echo "<select id='Categoria' name='categ'>";
    echo "<option>--SELECCIONE--</option>";
    mostrarCategs($cnx);
    echo "</select>";
}
