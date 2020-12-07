package com.laioffer.TravelPlanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "itinerary")
public class Itinerary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name="is_completed")
    private Boolean isCompleted;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy="itinerary", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<ItineraryItem> itineraryItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ItineraryItem> getItineraryItem() {
        return itineraryItem;
    }

    public void setItineraryItem(List<ItineraryItem> itineraryItem) {
        this.itineraryItem = itineraryItem;
    }
}
