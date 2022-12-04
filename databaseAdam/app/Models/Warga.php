<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Warga extends Model
{
    use HasFactory;
    use SoftDeletes;
    protected $table = "warga";
    protected $primaryKey = "wa_id";
    public $incrementing = true;
    public $timestamps = true;
}
