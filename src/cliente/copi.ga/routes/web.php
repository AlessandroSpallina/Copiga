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


// ROUTE FRONT PAGE
Route::view('/', 'welcome');
Route::view('/joinus', 'welcome_joinus')->name('joinus');

Auth::routes(['verify' => true]);

// ROUTE AUTH
Route::get('/login/printshop', 'Auth\LoginController@showPrintshopLoginForm')->name('login/printshop');
Route::get('/register/printshop', 'Auth\RegisterController@showPrintshopRegisterForm')->name('register/printshop');
Route::post('/login/printshop', 'Auth\LoginController@printshopLogin');
Route::post('/register/printshop', 'Auth\RegisterController@createPrintshop');

// ROUTE CLIENTE @ findme eliminare 'home' e fare tutte le route cliente /pannello/opzione/qualcosa
Route::view('/home', 'home')->middleware('auth', 'verified')->name('home');
Route::get('/order-history', 'OrderController@showOrderHistory')->middleware('auth', 'verified')->name('order_history');
Route::get('/order', 'OrderController@showPrintshopsByCredits')->middleware('auth', 'verified');
Route::post('/order', 'OrderController@createOrder')->middleware('auth', 'verified')->name('order');
Route::post('/order/confirm', 'OrderController@confirmOrder')->middleware('auth', 'verified')->name('order/confirm');

// ROUTE COPISTERIA
Route::view('/printshop', 'printshop')->middleware('auth:printshop')->name('printshop');
Route::resource('/printshop/files', 'FileController')->middleware('auth:printshop');
Route::resource('/printshop/papers', 'PaperController')->middleware('auth:printshop');
Route::resource('/printshop/bookbindings', 'BookbindingController')->middleware('auth:printshop');
Route::get('/printshop/printspecs', 'PrintspecsController@showPrintspecs')->middleware('auth:printshop')->name('printspecs');
Route::post('/printshop/printspecs', 'PrintspecsController@createPrintspecs')->middleware('auth:printshop');
