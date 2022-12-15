<?php
/**
 * Utilizo esta función para evitar errores con la información del HEADER
 */
    ob_start();
?>
    <head>
        <title>Creador exámenes ALFONSO XI</title>
        <link rel="stylesheet" href="./escuela.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.84.0">

        <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sticky-footer/">
    </head>
<body>
<?php
session_start();
require_once "../conexionBD.php";
require_once "../Modelos/controladorAlumno.php";
require_once "../Modelos/controladorProfe.php";

    if (isset($_SESSION['email'])) {
        $em = $_SESSION['email'];
        try {
            $cnx = conexionBD::getConexion();
            $result = $cnx->query("SELECT nombre, apellidos FROM usuarios WHERE email LIKE '{$em}'");
            $res = $result->fetch(PDO::FETCH_ASSOC);
        } catch(PDOException $exc) {
            header('Location: ../errorCNX.php');
        }

        if ($result->rowCount() > 0) {
                    $nomb = $res['nombre'];
                    $apel = $res['apellidos'];
            if (isset($_SESSION['admin'])) { //Diferentes cabeceras para tipos de usuario
                require_once "cabecera1.php";
            } elseif (isset($_SESSION['alumno']) || isset($_SESSION['profe'])) {
                require_once "cabecera2.php";
            }

        } else {
            $_SESSION['errorLogin'] = true;
            header('Location: ../index.php');
        }

    } else {
        $_SESSION['errorLogin'] = true;
        header('Location: ../index.php');
    }

/**
 * MENÚ USADO PARA MOSTRAR LAS OPCIONES DE LOS PROFESORES,
 * Y ALUMNOS;;
 *
 * Dependiendo de si hemos iniciado sesión como alumno o profesor, se usará una vista u otra
 */
?>
<div class="menu1">
    <div class="titulo1">
        <h2>MENÚ PRINCIPAL</h2>
        <p>¿Qué desea hacer?</p>
    </div>
    <div class="acciones1">
        <?php
            if (isset($_SESSION['admin'])) {
                header('Location: vista_admin.php');
            }
            if (isset($_SESSION['alumno'])) {
                require_once "vista_alumno.html";
            } elseif (isset($_SESSION['profe'])) {
                require_once "vista_profesor.html";
            }
        ?>
    </div>
    <?php

    if (isset($_REQUEST['cPreguntas'])) {
        header('Location: crearPregunta.php');
    }
    if (isset($_REQUEST['cExamenes'])) {
        header('Location: crearExamen.php');
    }
    if (isset($_REQUEST['vExamenes'])) {
        verExamenesHechos($cnx, recogerIDus($cnx));
    }
    if (isset($_REQUEST['vPreguntas'])) {
        header('Location: verPreguntas.php');
    }
    ?>
</div>
<footer>
    <?php
        require_once "footer.php";
    ?>
</footer>
</body>
<?php
ob_end_flush();
?>