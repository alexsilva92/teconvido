<!DOCTYPE html>
<html lang="es">
  <head>
    <?php include $_SERVER["DOCUMENT_ROOT"].'/route.php'; ?>
    <?php include $root.View::$Head; ?>
    <?php include $root.Module::$InputTown ?>
    <link rel="stylesheet" href="/public/jquery/jquery-ui.css">
  </head>

  <body>
      <div class="ui-widget">
        <label for="origin">Tags: </label>
        <input id="origin">
      </div>
      
      <div class="ui-widget">
        <label for="destination">Tags: </label>
        <input id="destination">
      </div>
      
  </body>
</html>
