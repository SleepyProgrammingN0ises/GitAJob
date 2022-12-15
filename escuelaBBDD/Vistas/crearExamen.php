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
require_once "../Modelos/controladorProfe.php";
require_once "../conexionBD.php";

$cnx = conexionBD::getConexion();

echo "<br>";
echo "<h2>Nuevo Exámen</h2>";
echo "<div class='crearExam'>
            <form action='' method='post'>
             <label for='Categoria'>Categoría preguntas: </label>";
if (isset($_REQUEST['setNPreguntas'])) {
    echo "<select id='Categoria' name='categ' value='{$_REQUEST['categ']}'>";
    mostrarCategs($cnx);
    echo "<option>--SELECCIONE--</option>";
    echo "</select>";
    echo "&nbsp;<button class='butonGen' type='submit'>+ Seleccionar</button>";
} else {
    echo "<select id='Categoria' name='categ'>";
    echo "<option>--SELECCIONE--</option>";
    mostrarCategs($cnx);
    echo "</select>
    &nbsp;<button class='buton1' type='submit'>+ AÑADIR</button>
        <br>
            <label for='tituloTest'>Titulo</label>
            <input type='text' placeholder='examen_1daw'/>
        <br>
            <label for='nPreg'>Nº Preguntas: </label>
            <input type='number' min='1' max='99' placeholder='máx. 99'/>";
    echo "<p class='peq'>*NOTA: si el limite es mayor que el nº de preguntas en el banco <br>
            se insertarán sólamente las que esten de la categoria especificada</p>";

    echo "<br><hr>";
    echo "<br>
          <label for='inicio'>Hora inicio</label>
          <input id='inicio' type='time' name='hInicio' placeholder='00:00'/>
          <label for='durac'>Duracion (hh:mm)</label>
          <input type='time' id='durac' name='hDurac' placeholder='00:50'/>";

    echo "<br><label for='curso'>Para el curso...</label>";
    mostrarCursosSelect($cnx);
}

echo "</form>";
echo "</div>";

require_once "footer.php";
