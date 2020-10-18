import kotlin.math.roundToInt

fun main() {
    var monthSum = 0

    var transferSum = 1_000_00 // всё храним в копейках (?), "_" - логический разделитель и для копеек
    var commission = calcVkCommission(transferSum, monthSum, "Vk Pay")
    monthSum += transferSum + commission
    println("Transfer sum: $transferSum kopeek, commission: $commission kopeek")

    transferSum = 5_000_00
    commission = calcVkCommission(transferSum, monthSum, "Mastercard Maestro")
    monthSum += transferSum + commission
    println("Transfer sum: $transferSum kopeek, commission: $commission kopeek")

    transferSum = 80_000_00
    commission = calcVkCommission(transferSum, monthSum, "Mastercard Maestro")
    monthSum += transferSum + commission
    println("Transfer sum: $transferSum kopeek, commission: $commission kopeek")

    transferSum = 10_000_00
    commission = calcVkCommission(transferSum, monthSum, "Visa Mir")
    monthSum += transferSum + commission
    println("Transfer sum: $transferSum kopeek, commission: $commission kopeek")
}

fun calcVkCommission(transferSum: Int, monthSum: Int = 0, cardType: String = "Vk Pay"): Int {
    // максимальные суммы в лимитах не учитываем (?)
    // полагаем перевод между счетами одного вида (?)
    val perc06Sum = (transferSum / 100 * 0.6).roundToInt()
    val perc075Sum = (transferSum / 100 * 0.75).roundToInt()
    return when (cardType) {
        // без комиссии
        "Vk Pay" -> 0
        // 0 при сумме ежемесячных платежей до 75000, иначе 0.6% + 20р
        "Mastercard Maestro" -> if (transferSum + monthSum <= 75_000_00) 0 else perc06Sum + 20_00
        // 0.75%, минимум 35р
        "Visa Mir" -> if (perc075Sum <= 35_00) 35_00 else perc075Sum
        else -> 0
    }
}