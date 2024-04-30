package Library.Entities;

import Library.Map.Map;

import java.util.ArrayList;
import java.util.List;

/** Generic entity controller template
 * @author poacher
 */
public abstract class GenericEntityController {
    protected ArrayList<GenericEntity> entities;
    private Map map;

    public GenericEntityController() {
        this.entities = new ArrayList<GenericEntity>();
    }

    public List<GenericEntity> getEntities() {
        return entities;
    }

    public void setEntity(int index, GenericEntity entity) {
        this.entities.set(index, entity);
    }

    public void addEntity(GenericEntity entity) {
        this.entities.add(entity);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void removeAll() {
        entities = new ArrayList<>();
    }

    public abstract void move();

    public abstract void miscLogic();
}
