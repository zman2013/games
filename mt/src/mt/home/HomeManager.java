package mt.home;

import com.badlogic.gdx.math.Vector2;

public class HomeManager {

	private Vector2 playerButtonCoordinate;
	
	private Vector2 fighterButtonCoordinate;
	
	private Vector2 shopButtonCoordinate;
	
	private Vector2 formationButtonCoordinate;
	
	public HomeManager(){
		shopButtonCoordinate = new Vector2( 117, 200 );
		formationButtonCoordinate = new Vector2( 117, 300 );
		fighterButtonCoordinate = new Vector2( 117, 400 );
		playerButtonCoordinate = new Vector2( 117, 500 );
	}

	public Vector2 getPlayerButtonCoordinate() {
		return playerButtonCoordinate;
	}

	public Vector2 getFighterButtonCoordinate() {
		return fighterButtonCoordinate;
	}

	public Vector2 getShopButtonCoordinate() {
		return shopButtonCoordinate;
	}

	public Vector2 getFormationButtonCoordinate() {
		return formationButtonCoordinate;
	}
	
}
