<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::group([

    //'middleware' => 'api',
    'prefix' => 'v1'

], function ($router) {

    Route::post('login', 'AuthController@login');
    Route::post('logout', 'AuthController@logout');
    Route::post('refresh', 'AuthController@refresh');
    Route::post('me', 'AuthController@me');

    Route::post('diffjobs', 'AuthController@diffjobs');
    Route::post('accept', 'AuthController@accept');
    Route::post('print', 'AuthController@print');
    /*Route::post('newtask', 'AuthController@newtask');
    Route::post('accepted', 'AuthController@accepted');
    Route::post('printed', 'AuthController@printed');*/

});
