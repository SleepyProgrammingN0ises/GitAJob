<head>
    <link rel="stylesheet" href="./escuela.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
</head>
<body>
<?php
require_once "./cabecera1.php";
require_once "../Modelos/controladorAdmin.php";
?>
<br>
<div class="menu1">
    <div class="titulo1">
        <h2>MENÚ PRINCIPAL</h2>
        <p>¿Qué desea hacer?</p>
    </div>
    <div class="acciones1">
        <?php
        if (isset($_SESSION['profe'])) {
            include_once "vista_profesor.html";
        } elseif (isset($_SESSION['alumno'])) {
            include_once "vista_alumno.html";
        }

        if (isset($_REQUEST['cPreguntas'])) {
            header('Location: crearPregunta.php');
        }
        if (isset($_REQUEST['cExamenes'])) {
            header('Location: crearExamen.php');
        }
        if (isset($_REQUEST['vExamenes'])) {
            header('Location: verExamenes.php');
        }
        if (isset($_REQUEST['vPreguntas'])) {
            header('Location: verPreguntas.php');
        }
        ?>
    </div>
</div>
</body>