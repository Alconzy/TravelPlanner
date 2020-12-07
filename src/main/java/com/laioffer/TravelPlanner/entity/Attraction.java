package com.laioffer.TravelPlanner.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="attraction")
public class Attraction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String placeId;

    // first item of types array returned by Goolge place api
    private String type;

    private Double latitude;
    private Double longitude;

    @Column(name="price_level")
    private Double priceLevel;

    private Double rating;

    private String name;

    @Column(name="website")
    private String placeWebsite;

    @Column(name="photo_ref")
    private String photoRef;

    @OneToMany(mappedBy="attraction", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<ItineraryItem> itineraryItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Double priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceWebsite() {
        return placeWebsite;
    }

    public void setPlaceWebsite(String placeWebsite) {
        this.placeWebsite = placeWebsite;
    }

    public String getPhotoRef() {
        return photoRef;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }

    public List<ItineraryItem> getItineraryItem() {
        return itineraryItem;
    }

    public void setItineraryItem(List<ItineraryItem> itineraryItem) {
        this.itineraryItem = itineraryItem;
    }
}
