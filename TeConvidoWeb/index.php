<!DOCTYPE html>
<html lang="es">
  <head>
    <?php include 'route.php'; ?>
    <?php include $root.View::$Head; ?>
    <link href="/public//css/bootstrap-dialog.css" rel="stylesheet" type="text/css">
    <script src="/public/js/bootstrap-dialog.min.js"></script>
    <?php include $root.View::$Dialog; ?>
    <?php include $root.Module::$InputTown ?>
    <link rel="stylesheet" href="/public/jquery/jquery-ui.css">
  </head>

  <body>
    <?php include $root.View::$Navbar; ?>
   
    <div class="jumbotron">
        <div class="container">
          <img class="h1" src="/public/images/logo_header_346x75.png" alt="encabezado"/>

          <p><b>Busca tu destino en la provincia de Teruel</b></p>

          <form class="form-inline" role="form">
              <div class="form-group ui-widget">
                    <label for="origin"></label>
                    <input id="origin" type="text" placeholder="Origen" class="form-control" required>
                  </div>
              <div class="form-group ui-widget">
                <label for="tags"></label>
                <input id="destination" type="text" placeholder="Destino" class="form-control" required>
              </div>
              <div class="form-group">
                <input type="date" placeholder="Fecha" class="form-control" required>
              </div>
              <button type="text" class="btn btn-primary">Buscar</button>
          </form>
        </div>
    </div>
      
    <?php
        if(isset($_POST['error'])){
            showDangerDialog("Login incorrecto","Tu email o tu contraseña son incorrectos");
        }
    ?>

    <?php include $root.View::$Footer; ?>
  </body>
</html>