package utils.objects.PlaceAPITesting;

//enum is special class in java which has collection of constants or  methods
public enum Resources {

    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    private String resource;
    // the items above will be fed into this string and will become the resource, many variables with only one get/set

    Resources(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }
}

