package com.example.ygocardsearcher;

public class CartaModel {
    private Integer id;
    private String name;
    private String type;
    private String desc;
    private String atk;
    private String def;
    private String level;
    private String race;
    private String attribute;
    private String archetype;
    private String image_url;

    public CartaModel() {}

    public CartaModel(Integer id, String name, String type, String desc, String atk, String def, String level, String race, String attribute, String archetype, String image_url) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.atk = atk;
        this.def = def;
        this.level = level;
        this.race = race;
        this.attribute = attribute;
        this.archetype = archetype;
        this.image_url = image_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        else if(o.getClass().equals(this.getClass())){
            CartaModel p = (CartaModel)o;
            if(p.id == this.id){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        final int primo = 7;
        int retorno = primo * this.id.hashCode();
        return retorno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb = sb.append("ID: ");
        sb = sb.append(this.id);
        sb = sb.append(" - " + "Nombre: ");
        sb = sb.append(this.name);
        sb = sb.append(" - " + "Tipo: ");
        sb = sb.append(this.type);
        return sb.toString();
    }
}
