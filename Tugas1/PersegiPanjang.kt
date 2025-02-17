class PersegiPanjang(val panjang: Int, val lebar: Int){
    fun hitungLuas(): Int {
        return panjang * lebar
    }
    
    fun hitungKeliling(): Int {
        return 2 * (panjang + lebar)
    }
}
    
    fun main(args: Array<String>){
        if (args.size < 2) {
            println("Masukan Argumen dengan Benar (Panjang Lebar)")
            return
        }
        
        val panjang = args[0].toIntOrNull()
        val lebar = args[1].toIntOrNull()
        
        if (panjang == null || lebar == null){
            println("Masukan Angka yang Valid")
            return
        }
        
        val pp = PersegiPanjang(panjang, lebar)
        println("Panjang : $panjang")
        println("Lebar : $lebar")
        println("Luas : ${pp.hitungLuas()}")
        println("Keliling : ${pp.hitungKeliling()}")
    }