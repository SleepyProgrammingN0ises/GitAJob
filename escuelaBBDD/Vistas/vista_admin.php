<head>
    <link rel="stylesheet" href="./escuela.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Creador exámenes - Menú ADMINISTRADOR</title>
</head>

<?php
session_start();
require_once "../conexionBD.php";
require_once "./cabecera1.php";
require_once "../Modelos/controladorAdmin.php";
?>
<div class="menuAdmin">
    <h2>MENÚ ADMÓN.</h2>
    <p>¿Qué desea hacer?</p>
    <form action="" method="post">
    <div class="botonsMenu">
        <button name="cambiarTipo">
            Gestionar alumnos/profesores
        </button>
        <button name="cCategorias">
            Gest. Categorías
        </button>
        <button name="cCursos">
            Gestionar Cursos
        </button>
    </div>
    </form>

    <?php
    try {
        $cnx = conexionBD::getConexion();
    } catch (PDOException $exc) {
        $_SESSION['errors'] = $exc->getMessage();
    }
        //botones vistas
        if (isset($_REQUEST['cambiarTipo'])) {
            echo "<h2>Gestión usuarios</h2>";
            if (listarUsuariosAdmin($cnx)) {
                $_SESSION['errors'] = "Usuarios encontrados!";
            } else {
                $_SESSION['errors'] = "ERROR. No se encuentran USUARIOS en la BD!";
            }
        }
        if (isset($_REQUEST['cCategorias'])) {
            echo "<h2>Gestión categorias</h2>";
            mostrarTablaCategs($cnx);
        }
        if (isset($_REQUEST['cCursos'])) {
            echo "<h2>Gestión cursos</h2>";
            mostrarTablaCursos($cnx);
        }
    ?>
    <?php
        //ACCIONES
        if (isset($_REQUEST['agregarCateg'])) {
            echo "<table>";
            echo "<form action='' method='post'>";
            echo "<thead>
                <th colspan='2'>NUEVA Categoría</th>
            </thead>";
            echo "<tbody>
                <tr><td>Nombre:</td>
                <td><input type='text' name='nCateg' required aria-required='true'/></td></tr>
                <tr><td colspan='2'><button name='insCategoria'>Crear categ.</button></td></tr>
            </tbody>";
            echo "</form>";
            echo "</table>";
        }
        if (isset($_REQUEST['insCategoria'])) {
            if (!empty($_REQUEST['nCateg'])) {
                if (insertarCategoria($cnx, $_REQUEST['nCateg'])) {
                    $_SESSION['errors'] = "INSERTADO Correctamente!";
                } else {
                    $_SESSION['errors'] = "Error al insertar! Póngase en contacto con admin.";
                }
            } else {
                $_SESSION['errors'] = "Rellene todos los campos, por favor!";
            }
        }

        if (isset($_REQUEST['cambiarUsu'])) {
            $apellido = explode('-- ', $_REQUEST['users']);
            $apellido = $apellido[0];
            //recojo el campo del Usuario seleccionado, y de ahi saco su apellido
            if (cambiarTipoUsuario($cnx, $_REQUEST['tipos'], $apellido)) {
                $_SESSION['errors'] = "ESTADO cambiado a " . $_REQUEST['tipos'] . " correctamente!";
            } else {
                $_SESSION['errors'] = "No se ha podido cambiar el ESTADO...";
            }
        }

        if (isset($_REQUEST['agregarCurso'])) {
            mostrarTablaCursos($cnx);
            echo "<form action='' method='post'>";
            insertarCurso();
            echo "</form>";
        }

        if (isset($_REQUEST['insCurso'])) {
            if (insertarCursoBD($cnx, $_REQUEST['newCurso'])) {
                $_SESSION['errors'] = "Nuevo curso ingresado!";
            }
        }
    ?>
</div>

<footer>
    <?php
        require_once "footer.php";
    ?>
</footer>
</body>