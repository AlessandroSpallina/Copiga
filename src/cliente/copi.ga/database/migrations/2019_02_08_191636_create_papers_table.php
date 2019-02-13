<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePapersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('papers', function (Blueprint $table) {
            $table->increments('id');
            $table->timestamps();
            $table->string('formato');
            $table->string('descrizione')->nullable();
            $table->float('prezzoBN')->nullable();
            $table->float('prezzoC')->nullable();
            $table->boolean('selezionato')->default(false);
            $table->unsignedInteger('printshop_id');
            $table->foreign('printshop_id')->references('id')->on('printshops');
            $table->unique(array('printshop_id', 'formato'));
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('papers');
    }
}
