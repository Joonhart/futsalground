package com.futsalground.portfolio.ground.domain;

import com.futsalground.portfolio.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@SequenceGenerator(
        name = "ground_seq_generator",
        sequenceName = "ground_seq", allocationSize = 1)
@Table(name = "t_ground")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ground extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ground_seq_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "grd_name")
    private String grdName;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "road_addr")
    private String roadAddr;
    @Column(name = "num_addr")
    private String numAddr;
    @Embedded
    private TimeAndCost timeAndCost;
    @Column(name = "match_type")
    private char matchtype;
    @Embedded
    private Amenities amenities;
    @Embedded
    private Images images;
    @Column(name = "size1")
    private int size1;
    @Column(name = "size2")
    private int size2;
    @Embedded
    private GroundInfo groundInfo;
    @Column(name = "isView")
    private char isView;

    @Builder
    public Ground(Long id, String grdName, String name, String phone, String roadAddr, String numAddr, TimeAndCost timeAndCost, char matchtype, Amenities amenities, Images images, int size1, int size2, GroundInfo groundInfo, char isView) {
        this.id = id;
        this.grdName = grdName;
        this.name = name;
        this.phone = phone;
        this.roadAddr = roadAddr;
        this.numAddr = numAddr;
        this.timeAndCost = timeAndCost;
        this.matchtype = matchtype;
        this.amenities = amenities;
        this.images = images;
        this.size1 = size1;
        this.size2 = size2;
        this.groundInfo = groundInfo;
        this.isView = isView;
    }
}