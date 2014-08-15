<!DOCTYPE html>
<html lang="es">
    <head>
        <?php include $_SERVER["DOCUMENT_ROOT"].'/route.php'; ?>
        <?php include $root.View::$Head; ?>
        <?php include $root.View::$Dialog; ?>
        <?php include $root.Module::$InputTown ?>
        <?php $antsRoad = array("Home" => Address::$Home); ?>
    </head>

    <body>
        <?php include $root.View::$Navbar; ?>
        
        <div class="container">
            <div class="container-teconvido">

                <?php include $root.Module::$AntsRoad?>
                
                <div class="panel panel-success">
                    <div class="panel-body container-home">
                        <img src="/public/images/logo_header_307x75.png" alt="encabezado"/>
                        <p><b>Busca tu destino en la provincia de Teruel</b></p>
                    </div>
                    <div class="panel-footer">
                        <form action="<?=Address::$SearchTravel?>">
                            <div class="row">
                                <div class="col-xs-3 ui-widget">
                                    <input class="form-control input-town input-home input-origin-lg" 
                                           name="origin" type="text" placeholder="Origen"  required>
                                </div>

                                <div class="col-xs-3 ui-widget">
                                    <input name="destination" class="form-control input-town input-home input-destination-lg" type="text" placeholder="Destino" required>          
                                </div>

                                <div class="col-xs-3">
                                    <input name="date" class="form-control input-home input-date-lg" type="date" placeholder="Fecha"  required>
                                </div>

                                <div class="col-xs-3">
                                    <button type="text" class="btn btn-primary btn-lg width-100x100">Buscar</button>
                                </div>

                            </div>
                            <input name="type_advertiser" value ="driver" type="hidden">
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <?php
        if (isset($_POST['error'])) {
            showDangerDialog("Login incorrecto", "Tu email o tu contraseña son incorrectos");
        }
        ?>

        <?php include $root.View::$Footer; ?>
    </body>
</html>