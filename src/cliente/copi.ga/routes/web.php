<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

/*Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');*/


Route::view('/', 'welcome');
Route::view('/joinus', 'welcome_joinus')->name('joinus');

Auth::routes();

Route::get('/login/printshop', 'Auth\LoginController@showPrintshopLoginForm')->name('login/printshop');
Route::get('/register/printshop', 'Auth\RegisterController@showPrintshopRegisterForm')->name('register/printshop');

Route::post('/login/printshop', 'Auth\LoginController@printshopLogin');
Route::post('/register/printshop', 'Auth\RegisterController@createPrintshop');


Route::view('/home', 'home')->middleware('auth');
Route::view('/printshop', 'printshop')->middleware('auth:printshop')->name('printshop');

Route::resource('files', 'FileController')->middleware('auth:printshop');
