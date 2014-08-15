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

$root = __DIR__;
$host = "localhost";

class Model {
    static $User = "/models/user.php";
    static $TicketLogin = "/models/ticket_login.php";
    static $Url = "/models/url.php";
    static $Travel = "/models/travel.php";
    static $TravelWithDriver = "/models/travel_with_driver.php";
    static $TravelStop = "/models/travel_stop.php";
}

class View {
    static $Navbar = "/views/include/navbar.php";
    static $Footer = "/views/include/footer.php";
    static $Head = "/views/include/head.php";
    static $Dialog = "/views/include/dialog.php";
    
    static $RegistrationPanel = "/views/include/registration_panel.php";
    static $LoginPanel = "/views/include/login_panel.php";
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

class Address{
    static $Home = "/";
    static $Register = "/register";
    static $Alert = "/my_profile?section=alert";
    static $MyTravels = "/my_profile?section=my_travels";
    static $MyComments = "/my_profile?section=my_comments";
    static $PreferencesProfile = "/my_profile?section=preferences";
    static $MyCars = "/my_profile?section=my_cars";
    static $MyProfile = "/my_profile";
    static $PublishTravel= "/publish_travel";
    static $SearchTravel = "/search_travel";
    static $GetCar = "/car";
}

class Module {
    static $Login = "/modules/login/index.php";
    static $InputTown = "/modules/input_town/index.php";
    static $AntsRoad = "/modules/ants_road/index.php";
    static $CheckSession = "/modules/check_session/index.php";
    static $CheckNotSession = "/modules/check_not_session/index.php";
    static $ListCar = "/modules/list_car/index.php";
    static $TableRequestedTravelsUser = "/modules/table_requested_travels_user/index.php";
    static $TableRealizedTravelsUser = "/modules/table_realized_travels_user/index.php";
    static $TableTravelsWithDriverUser = "/modules/table_travels_with_driver_user/index.php";
    static $TableCarsUser = "/modules/table_cars_user/index.php";
    static $SetInputWithGet = "/modules/set_input_with_get/index.php";
}

class Action{
    static $StartSession = '/action/start_session.php';
    static $CloseSession = '/action/close_session.php';
    static $Register = '/action/register.php';
    static $AddTravel = '/action/add_travel.php';
    static $AddTravelWithDriver = '/action/add_travel_with_driver.php';
}

class IU{
    static $MyProfile = "/iu/my_profile/index.php";
    static $Register = "/iu/register/index.php";
    static $PublishTravel = "/iu/publish_travel/index.php";
    static $SearchTravel = "/iu/search_travel/index.php";
    static $GetCar = "/iu/get_car/index.php";
}

class Utilities{
    private static $LEN_DATE = 3;
    private static $YEAR = 0;
    private static $MONTH = 1;
    private static $DAY = 2;
    
    static function inputError($error){
        if($error){
            echo "input-error";
        }
    }
    
    static function checkDate($date){
        if($date != NULL){
            $parts = explode("-", $date);
            if(count($parts) == Utilities::$LEN_DATE){
                if(!empty($parts[Utilities::$DAY])&&
                   !empty($parts[Utilities::$MONTH]) &&
                   !empty($parts[Utilities::$YEAR])){
                    return checkdate(
                            $parts[Utilities::$MONTH], 
                            $parts[Utilities::$DAY],
                            $parts[Utilities::$YEAR]);  
                }else{
                    return FALSE;
                }
            }else{
                return FALSE;
            }
        }else{
            return FALSE;
        }
    }
    
    static function GET($key){
        if(isset($_GET[$key]) && !empty($_GET[$key])){
            return $_GET[$key];
        }else{
            return NULL;
        }
    }
    
    static function POST($key){
        if(isset($_POST[$key]) && !empty($_POST[$key])){
            return $_POST[$key];
        }else{
            return NULL;
        }
    }
}