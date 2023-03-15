import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import me.trqhxrd.octorpg.Main
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseTest {

    protected lateinit var server: ServerMock
    protected lateinit var plugin: Main

    @BeforeEach
    fun setUp() {
        this.server = MockBukkit.mock()
        this.plugin = MockBukkit.load(Main::class.java)
    }

    @AfterEach
    fun tearDown() {
        MockBukkit.unmock()
    }
}