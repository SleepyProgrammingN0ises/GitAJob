<?php
session_start();
ob_start();
?>
<head>
    <title>Login - Creador exámenes</title>
    <link rel="stylesheet" href="./Vistas/login.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sticky-footer/">
</head>
<body>
<?php
require_once "conexionBD.php";
require_once "./Vistas/cabecera0.php";
/**
 * @param mixed $email
 * @param mixed $cont
 * @param PDOStatement $result
 * @return bool
 * Comprueba el email, el password y el tipo de usuario [tipoUser] en la BBDD
 */
function checkPswrd(mixed $email, mixed $cont, PDOStatement $result) {
    $fet = $result->fetch(PDO::FETCH_ASSOC);
    $todoBien = true;
    $tipoUser = 0;
    if ($fet !== false) {
            $val = $fet['email'];
                if ($val == $email) {} else {$todoBien = false;}
            $val = $fet['password'];
                if (password_verify($cont, $val)) {} else {$todoBien = false;}
            $val = $fet['estado'];
                switch ($val) {
                    case '1':
                        $tipoUser = 1; //Admin
                        break;
                    case '2':
                        $tipoUser = 2; //Profe
                        break;
                    case '3':
                        $tipoUser = 3; //Alumno
                        break;
                    default:
                        $tipoUser = null;
                        $todoBien = false;
                        break;
                }
    } else {
            $todoBien = false;
    }
    if (!$todoBien) {
        return null;
    }
    return $tipoUser; // retornara true si los datos de quien ingresa son correctos/se han encontrado
}

if (isset($_SESSION['email'])) {
    //si hemos entrado ya
    header('Location: ./Vistas/escuela.php');
}

if (isset($_REQUEST['entrar'])) {
    try {
        $cnx = conexionBD::getConexion();

        $email = $_REQUEST['email'];
        $cont = $_REQUEST['contra'];
        $resultQ = $cnx->query("SELECT email, password, estado FROM usuarios WHERE email LIKE '{$email}'");

        if ($resultQ != null) {
            if (checkPswrd($email, $cont, $resultQ) != null) {
                $_SESSION['errors'] = "Entrado correctamente!";
                $_SESSION['email'] = $email;
                header('Location: ./Vistas/escuela.php');
            } else {
                echo "<fieldset><legend class='error'>
                ERROR: DATOS DEL USUARIO INCORRECTOS//NO EN BBDD!!
                </legend></fieldset>";
            }
        } else {
            header('Location: errorCNX.php');
        }
    } catch (PDOException $ex) {
        $_SESSION['errors'] = $ex->getMessage();
        header('Location: errorCNX.php');
    } catch (Exception $e) {
        $_SESSION['errors'] = $e->getMessage();
        header('Location: errorCNX.php');
    }
}
    if (isset($_SESSION['errorLogin']) && $_SESSION['errorLogin']) {
        //si venimos de haber fallado, lo explicamos al usuario y ponemos el error a false
        echo "<p class='error1'>ERROR los datos no coinciden // No se encuentran en la BBDD!</p>";
    }
    echo "<fieldset class='login'>
          <legend>Iniciar sesión</legend>
          <table>
          <tbody>
          <form action='' method='post'>
            <tr>
               <td><label for='login'>E-mail: </label></td>
               <td><input id='login' name='email' type='text' required aria-required='true'/></td>
            </tr>
            <tr>
                <td><label for='cont'>Contra: </label></td>
                <td><input id='cont' name='contra' type='password' required aria-required='true'/></td>
            </tr>
            <tr>
                <td><button type='submit' name='entrar'>Enviar</button></td>
                <td><button type='reset'>Reset</button></td>
</tr>
            </form>
          </tbody>
</table>
</fieldset>
<p>¿No tienes cuenta? <a href='inscribirse.php'>Regístrate!</a></p>";
?>
</body>
<?php
require_once "./Vistas/footer.php";
ob_end_flush();

?>