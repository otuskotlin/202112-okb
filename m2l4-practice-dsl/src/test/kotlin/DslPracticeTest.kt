import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DslPracticeTest {
    @Test
    fun dslTest() {
        val solarSystem = starSystem {
            name = "Sun"
            mass = 1e12

            planet {
                name = "Mercury"
                mass = 1e5
            }
            planet {
                name = "Venus"
                mass = 1e6
            }
            planet {
                name = "Earth"
                mass = 1e6
                satellite {
                    name = "Moon"
                    mass = 1e4
                }
            }
        }

        assertEquals("Sun", solarSystem.name)
        assertTrue("Solar system must contain Earth") {
            solarSystem.children.any {
                println("planet: ${it::class.java} ${it.name}")
                it is Planet && it.name == "Earth"
            }
        }
    }
}

fun starSystem(block: StarSystemDsl.() -> Unit): Star = StarSystemDsl().apply(block).build()

class StarSystemDsl : SpaceObjectDsl() {
    override fun build(): Star = Star(name, mass, children)

    fun planet(so: Planet) = add(so)
    fun planet(block: PlanetDsl.() -> Unit) = add(PlanetDsl().apply(block))
}

class PlanetDsl: SpaceObjectDsl() {
    override fun build(): SpaceObject = Planet(name, mass, children.toList())

    fun satellite(so: Satellite) = add(so)
    fun satellite(block: SatelliteDsl.() -> Unit) = add(SatelliteDsl().apply(block))
}

class SatelliteDsl: SpaceObjectDsl() {
    override fun build(): SpaceObject = Satellite(name, mass, children.toList())
}

abstract class SpaceObjectDsl {
    var name: String = ""
    var mass: Double = 0.0

    protected val children: MutableList<SpaceObject> = mutableListOf()

    protected fun add(so: SpaceObject) {
        children.add(so)
    }

    protected fun add(sod: SpaceObjectDsl) {
        add(sod.build())
    }

    abstract fun build(): SpaceObject
}

sealed interface ISpaceObject
sealed class SpaceObject(
    val name: String,
    val mass: Double,
    val children: List<SpaceObject> = listOf()
) : ISpaceObject

class Galaxy(name: String, mass: Double, children: List<SpaceObject> = listOf()) : SpaceObject(name, mass, children) {}
class Star(name: String, mass: Double, children: List<SpaceObject> = listOf()) : SpaceObject(name, mass, children) {}
class Planet(name: String, mass: Double, children: List<SpaceObject> = listOf()) : SpaceObject(name, mass, children) {}
class Satellite(name: String, mass: Double, children: List<SpaceObject> = listOf()) : SpaceObject(name, mass, children) {}
class Asteroid(name: String, mass: Double, children: List<SpaceObject> = listOf()) : SpaceObject(name, mass, children) {}
