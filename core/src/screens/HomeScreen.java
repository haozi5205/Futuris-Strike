package screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.futuris.strike.FuturisStrikeGame;

public class HomeScreen implements Screen {
	private FuturisStrikeGame game;
	private Texture startPlayingButton;
	private Texture startPlaying;
	private Texture controlsButton;
	private SpriteBatch batch;
	private Sprite sprite;
	
	private ShapeRenderer shapeRenderer;
	
	private float constant = 0f;
	private float stateTimer;
		
	public HomeScreen(FuturisStrikeGame game) {
		this.game = game;

		batch = new SpriteBatch();
		startPlayingButton = new Texture(Gdx.files.internal("blue_button.png"));
		startPlaying = new Texture(Gdx.files.internal("StartPlaying_small.png"));
		controlsButton = new Texture(Gdx.files.internal("ControlsButton.png"));
		
		shapeRenderer = new ShapeRenderer();
	}
	
	@Override 
	public void show() {
		
	}
	
	public void handleInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			game.setScreen(new GameScreen(game));
		}
	}
	
	public void create() {
	}

	@Override 
	public void render(float delta) {
		handleInput();
		
		Gdx.gl.glClearColor(10f / 256f,  18f / 256f,  20f / 256f,  0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.valueOf("0fffff"));
		
		
//		shapeRenderer.line(new Vector2(0, Gdx.graphics.getHeight() - 50), new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		
		for(int i = 20; i <= Gdx.graphics.getWidth(); i += 20) {
			shapeRenderer.line(new Vector2(i, 0), new Vector2(i - constant, Gdx.graphics.getHeight() + constant));
		}
		
		for(int i = 20; i <= Gdx.graphics.getHeight(); i += 20) {
			shapeRenderer.line(new Vector2(0, i), new Vector2(Gdx.graphics.getWidth() + constant, i - constant));
		}
		
		shapeRenderer.end();
		
		game.batch.begin();
		game.homeFont.draw(game.batch, "FUTURIS STRIKE", Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() - 40);
				
		game.batch.draw(controlsButton, 80, 80, Gdx.graphics.getWidth() - 80 - 80, 70);
		game.batch.draw(startPlayingButton, 80, 80 + 70 + 20, Gdx.graphics.getWidth() - 80 - 80, 70);
		
		game.batch.end();
		
		stateTimer += delta;
		if(stateTimer <= 3f) {
			constant += 0.1f;
		} else if(stateTimer > 6f && stateTimer % 4 == 0) {
			System.out.println("1");
		} else if(stateTimer > 6f && stateTimer % 4 != 0) {
			constant += 0.1f;
			System.out.println("2");
		} else if (stateTimer > 15) {
			stateTimer = 0;
		}
		
		
	}
	
	@Override
	public void resize(int width, int height) {
		
		
	}
	
	
	@Override
	public void pause() {
		
		
	}
	
	@Override
	public void resume() {
		
		
	}
	
	@Override 
	public void hide() {
		
	}
	
	@Override 
	public void dispose() {
		startPlayingButton.dispose();
	}	
}
