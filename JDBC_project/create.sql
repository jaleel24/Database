-- ===================================================================
-- Photography contest DB-------------

-- Schema Creation script------------
-- jaleel Sayal---------------
---215800493-----------------

-- -------------------------------------------------------------------
-- Schema definition

create table Sponsors
(
    name        varchar(20) not null,
    sponsorType varchar (20),
    level       int not null,
    constraint Sponsors_PK
        primary key (name)
);

create table PhotographyContest
(
    name    varchar(20) not null,
    year      int         not null,
    location  varchar(20) not null,
    from_date date        not null,
    to_date   date        not null,
    constraint PC_PK
        primary key (name, year)
);
create table Category
(
    name       varchar(20) not null,
    description varchar(20) not null,
    constraint Category_PK
        primary key (name)
);
create table Submissions
(
    caption     varchar(20) not null,
    format      varchar(20) not null,
    description varchar(50) ,
    size        int         ,
    reg_num        int         not null,
    name      varchar(20) not null,
    year        int         not null,
    constraint Submissions_PK
        primary key (caption),
    constraint Submissions_FK_PC
        foreign key (name, year) references PhotographyContest,
    constraint Submissions_FK_Photographer
        foreign key (reg_num) references Photographer

);
create table Photographer
(
    reg_num    int         not null,
    DOB     int         not null,
    country varchar(20) not null,
    email   varchar(50) not null,
    prname  varchar(20) not null,
    constraint Photographer_PK
        primary key (reg_num)
);

create table OpenCallExhibition
(
    caption     varchar(20) not null,
    format      varchar(20) not null,
    description varchar(50),
    size        int,
    reg_num     int       not null,
    name      varchar(20) not null,
    year        int         not null,
    constraint Open_call_exibition_PK
        primary key (caption, reg_num, name, year),
    constraint Open_call_exibition_FK
        foreign key (caption) references Submissions,
    constraint Open_call_exibition_FK
        foreign key (reg_num) references Photographer,
    constraint Open_call_exibition_FK
        foreign key (name,year) references PhotographyContest
);

create table FeaturedExhibition
(
    caption     varchar(20) not null,
    format      varchar(20) not null,
    description text(50),
    size           int,
    reg_num        int         not null,
    name      varchar(20) not null,
    year        int         not null,
    constraint Featured_exibition_PK
        primary key (caption, reg_num, name, year),
    constraint Featured_exibition_FK
        foreign key (caption) references Submissions,
    constraint Featured_exibition_FK
        foreign key (reg_num) references Photographer,
    constraint Featured_exibition_FK
        foreign key (name, year) references PhotographyContest
);

create table PhotoMagazine
(
    year          int         not null,
    title         varchar(20) not null,
    URL           varchar(20) not null,
    chief_editor  varchar(20) not null,
    constraint Photo_Magazine_PK
        primary key (year)
);

create table Members
(
    member_Num int         not null,
    name     varchar(20) not null,
    email     varchar(50) not null,
    country   varchar(20),
    expertise varchar(50),
    constraint Members_PK
        primary key (member_Num)
);

create table Nominators
(
    name    varchar(20) not null,
    year    int         not null,
    mem_id  int         not null,
    constraint Nominators_PK
        primary key (name, year, mem_id),
    constraint Nominators_FK
        foreign key (name, year) references PhotographyContest,
    constraint Nominators_FK
        foreign key (mem_id) references Members
);

create table Nominee
(
    caption     varchar(20) not null,
    format      varchar(20) not null,
    description varchar(50),
    size        int,
    reg_num     int         not null,
    name      varchar(20)   not null,
    year        int         not null,
    constraint Nominee_PK
        primary key (caption, reg_num, name, year),
    constraint Nominee_FK
        foreign key (caption) references Submissions,
    constraint Nominee_FK
        foreign key (reg_num) references Photographer,
    constraint Nominee_FK
        foreign key (name, year) references PhotographyContest
);

create table Award
(
    award_name   varchar(20) not null,
    prize  int         not null,
    name varchar(20) not null,
    year   int         not null,
    constraint Award_PK
        primary key (award_name),
    constraint Award_FK
        foreign key (name, year) references PhotographyContest
);
