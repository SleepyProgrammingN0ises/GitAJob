<?php
if (isset($_SESSION['errors'])) {
    echo "<div class='footer1'>
      <h2>Errores</h2>
      <p class='error1'>{$_SESSION['errors']}</p>
</div>";
} else {
    echo "<div class='footer1'>
      <h2>Errores</h2>
      <p class='error1'>No hay errores que mostrar</p>
</div>";
}
