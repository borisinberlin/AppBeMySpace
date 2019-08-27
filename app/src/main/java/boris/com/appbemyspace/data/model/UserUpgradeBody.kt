package boris.com.appbemyspace.data.model


data class UserUpgradeBody(
    val individual: Individual,
    val username: String
)

data class Individual(
    val address: Address,
    val dateOfBirth: String
)

data class Address(
    val city: String,
    val country: String,
    val postalCode: String
)