/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mode.tema;

import View.Main.FactoryPokemon.FactoryPokemon;
import View.Main.Halloween.FactoryHalloween;
import View.Main.Smileys.FactorySmileys;
import View.Main.SouthPark.FactorySouthPark;
import View.Main.War.FactoryWar;
import model.peca.PecaFactory;

/**
 *
 * @author Miguel
 */
public class TemaFactory extends AbstractFactory {

    @Override
    public PecaFactory getTema(String tema) {

        PecaFactory factory;

        if ("Halloween".equals(tema))
            factory = new FactoryHalloween();
        else if ("SouthPark".equals(tema))
            factory = new FactorySouthPark();
        else if ("Pokemon".equals(tema))
            factory = new FactoryPokemon();
        else if ("Smileys".equals(tema))
            factory = new FactorySmileys();
        else
            factory = new FactoryWar();

        return factory;

    }

}
