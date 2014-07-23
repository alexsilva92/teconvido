<?php

/* 
 * Copyright 2014 Alejandro Silva <alexsilva792@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

global $root;
$root = __DIR__;
$host = "localhost";

class Model {
    static $User = "/models/user.php";
    static $TicketLogin = "/models/ticket_login.php";
    static $Url = "/models/url.php";
    static $GoogleTravel = "/models/google_travel.php";
}

class View {
    public static $Navbar = "/views/include/navbar.php";
    public static $Footer = "/views/include/footer.php";
    public static $Head = "/views/include/head.php";
    public static $Dialog = "/views/include/dialog.php";
    public static $Register = "/views/register.php";
}

class Controller {
    static $DBConfig = "/controllers/database/db_config.php";
    static $DBConnect = "/controllers/database/db_connect.php";
    static $DBRoute = "/controllers/database/route.php";
    static $Session = "/controllers/safety/session.php";
    static $Cookies = "/controllers/safety/cookies.php";
    static $Client = "/controllers/request/client.php";
    static $UrlRequest = "/controllers/request/url_request.php";
    static $GoogleRequest = "/controllers/googleapi/google_request.php";
}

class Module {
    static $Login= "/modules/login/index.php";
    static $InputTown= "/modules/input_town/index.php";
}
