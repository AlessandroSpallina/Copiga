<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateBookbindingsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('bookbindings', function (Blueprint $table) {
            $table->increments('id');
            $table->timestamps();
            $table->string('tipo')->unique();
            $table->string('descrizione')->nullable();
            $table->float('prezzo')->nullable();
            $table->boolean('selezionato')->default(false);
            $table->unsignedInteger('printshop_id');
            $table->foreign('printshop_id')->references('id')->on('printshops');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('bookbindings');
    }
}
