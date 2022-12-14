<?php
    ob_start();
?>
    <head>
        <title>Login - Creador exámenes</title>
        <link rel="stylesheet" href="./escuela.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
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

            require_once "cabecera1.php";
        } else {
            $_SESSION['errorLogin'] = true;
            header('Location: ../index.php');
        }

    } else {
        $_SESSION['errorLogin'] = true;
        header('Location: ../index.php');
    }
?>
<div class="menu1">
    <div class="titulo1">
        <h2>MENÚ PRINCIPAL</h2>
        <p>¿Qué desea hacer?</p>
    </div>
    <div class="acciones1">
        <?php

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
<footer>
    <?php
        require_once "footer.php";
    ?>
</footer>
</body>
<?php
ob_end_flush();
?>