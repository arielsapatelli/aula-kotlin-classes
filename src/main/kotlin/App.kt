import br.com.caelum.stella.validation.CNPJValidator
import br.com.caelum.stella.validation.CPFValidator
import kotlin.Boolean as Boolean

class Pessoa(val name: String, val idade: Int, val documento: Documento) {

    fun estudar() {
        println("$name está estudando muito rapaz...")
    }
}


open class Documento(open val valor: String) {

    open fun validar(): Boolean{
        return false
    }
}

class CPF(override val valor: String) : Documento(valor){

    val cpfValidator = CPFValidator()

    override fun validar():Boolean{
        cpfValidator.assertValid(valor)
        return true
    }
}

class CNPJ(override val valor: String) : Documento(valor){
    val cnpjValidator = CNPJValidator()

    override fun validar():Boolean{
        cnpjValidator.assertValid(valor)
        return true
    }
}


fun main() {
    val pessoa1 = Pessoa(name = "Ariel Rossi Sapatelli", idade = 30, documento = CPF("374.970.758-88"))
    val pessoa2 = Pessoa(name = "Samuel Gomes", idade = 17, documento = CPF("571.265.018-54"))
    val BANCO_DE_DADOS = mapOf<String,Pessoa>(
                                        "374.970.758-88" to pessoa1,
                                        "571.265.018-54" to pessoa2)


    //val pessoaEncontrada = BANCO_DE_DADOS["418.361.798-71"]


    val pessoaEncontrada = BANCO_DE_DADOS["571.265.018-54"]

    if(pessoaEncontrada==null){

        println("nenhum registro encontrado sinto muito. só lamento")

    }else{
        println("Pessoa encontrada: e o documento:${pessoaEncontrada.documento.valor} é valido: ${pessoaEncontrada.documento.validar()}")
    }

}

