<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
      
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
        <a href="<?=Address::$Home?>"><img class="navbar-brand" src="/public/images/nav_logo_150x62.png" ></a>
    </div>
        
    <nav class="collapse navbar-collapse bs-navbar-collapse navbar-left" role="navigation">
      <ul class="nav navbar-nav">
          <li class="active" >
              <a href="<?=Address::$SearchTravel?>">Buscar Viaje</os-p></a>
          </li>
          <li class="active" >
              <a href="<?=Address::$PublishTravel?>">Publicar Viaje</os-p></a>
          </li>
      </ul>
    </nav>
     
    <?php include $root.Module::$Login; ?>
            
  </div>
</div>
