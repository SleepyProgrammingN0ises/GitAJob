<head>
    <link rel="stylesheet" href="./escuela.css"/>
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
    echo "<h2>Pregunta nueva</h2>";
    echo "<div class='crearPreg'>
            <form action='' method='post'>
             <label for='Categoria'>Categoría: </label>";
             if (isset($_REQUEST['setNR'])) {
                 echo "<select id='Categoria' name='categ' value='{$_REQUEST['categ']}'>";
                 echo "<option>--SELECCIONE--</option>";
                 mostrarCategs($cnx);
                 echo "</select>";
             } else {
                 echo "<select id='Categoria' name='categ'>";
                 echo "<option>--SELECCIONE--</option>";
                 mostrarCategs($cnx);
                 echo "</select>";
             }
            echo "<br>
            <label>Tipo pregunta:</label>
            <br><input id='RS' type='radio' name='pregT' value='S'/>
            <label for='RS'>Simple <span class='peq'>(v/f)</span></label>
            <br>
            <input id='RC' type='radio' name='pregT' value='RC'/>
            <label for='RC'>Resp. Corta <span class='peq'>(_____)</span></label>
            <br>
            <input id='RM' type='radio' name='pregT' value='RM'/>
            <label for='RM'>Resp. Múltiple <span class='peq'>(---, --, ----,...)</span></label
            <br><br>";
            if (isset($_REQUEST['setNR'])) {
                echo "<input id='nomP' type='text' name='nomP' placeholder='Enunciado' value='{$_REQUEST['nomP']}'/>";
            } else {
                echo "<input id='nomP' type='text' name='nomP' placeholder='Enunciado'/>";
            }
          echo "</div>";


    echo "<br>";
        echo "<div id='rsimp' class='invisible'>
                <label>Introduce la respuesta correcta</label><br>
                <input type='radio' id='si' name='sino' value='true'/>&nbsp;&nbsp;<label for='si'>Verdadero</label>
                <br>
                <input type='radio' id='no' name='sino' value='false'/>&nbsp;&nbsp;<label for='no'>Falso</label>
                <br><button type='submit' name='anadirResp' value='true'>AÑADIR RESPUESTA</button>
            </div>";
        echo "<div id='rcort' class='invisible'>
                <label for='rCorta'>Introduce las respuestas posibles (sep. por comas)</label><br>
                <input id='rCorta' type='text' name='respCorta' placeholder='xxxx,xxxxx,xxxx,...'/>
                <br><button type='submit' name='anadirResp' value='true'>AÑADIR RESPUESTA</button>
            </div>";
        echo "<div id='rmult' class='invisible'>
                <form action='' method='post'>
                <label for='numR'>Nº respuestas</label> <input id='numR' name='numR' type='number' min='2' max='5'/>
                    <br>    
                    <button type='submit' name='setNR'>FIJAR</button>
                </form>";
            echo "</div>";
        if (isset($_REQUEST['setNR'])) {
            echo "<form action='' method='post'>";
            for ($i = 0;$i<$_REQUEST['numR'];$i++) { //Pasamos por el name un vector con los checkboxes activos
                $str = "resp" . $i;
                echo "<input type='checkbox' name='respuestas[]' placeholder='{$str}'/>&nbsp;<input type='text' name='{$str}'";
                echo "<input type='hidden' name='numResp' value='{$_REQUEST['numR']}'/>";   //Num. respuestas
            }
            echo "</form>
            <br><button type='submit' name='anadirResp' value='true'>AÑADIR RESPUESTA</button>";
        }
        echo "</form>";

        if (isset($_REQUEST['anadirResp'])) {
            $arrayPregunt['categ'] = $_REQUEST['categ']; //categoria
            $arrayPregunt['pregT'] = $_REQUEST['pregT']; //tipo
            $arrayPregunt['nomP'] = $_REQUEST['nomP']; //enunciado
            $arrayPregunt['respCorta'] = $_REQUEST['respCorta'];
            if (!isset($_REQUEST['sino'])) {
                $arrayPregunt['sino'] = null;
            } else {
                $arrayPregunt['sino'] = $_REQUEST['sino'];
            }

            if (insertarPregunta($arrayPregunt)) {
                $_SESSION['errors'] = "Insertado correctamente!";
            } else {
                $_SESSION['errors'] = "ERROR al insertar!";
            }
        }

        require_once "./footer.php";
        ?>
<script>
    var form = document.forms[0];
    var contRS = document.getElementById('rsimp');
    var contRC = document.getElementById('rcort');
    var contRM = document.getElementById('rmult');
    var tiposPregunt = document.getElementsByName('pregT');

    for (let pre of tiposPregunt) {
        pre.addEventListener('change', cambiarVisib);
    }
    form.addEventListener('submit', validar);

    function cambiarVisib(e) {
        if (e.target.value == 'S') {    //resp. Simple
            contRC.className = "invisible";
            contRM.className = "invisible";
            contRS.className = "visible";
        }
        if (e.target.value == 'RC') {   //resp. Corta
            contRS.className = "invisible";
            contRM.className = "invisible";
            contRC.className = "visible";
        }
        if (e.target.value == 'RM') {   //resp. Multiple
            contRS.className = "invisible";
            contRC.className = "invisible";
            contRM.className = "visible";
        }
    }

    function validar(e) {
        let todoBien = true;
        if (document.getElementById('Categoria').value == '--SELECCIONE--') {
            todoBien = false;

        }
        if (!todoBien) {
            e.preventDefault();
        }
    }

</script>
