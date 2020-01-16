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
create table Support(
  name   varchar(20) not null,
  s_name varchar(20) not null,
  level   varchar(20) not null,
  year   int         not null,
  constraint Support_FK
      foreign key (s_name) references sponsor ,
  constraint Support_FK
      foreign key (name, year) references PhotographyContest

);
create table in1(
  c_name   varchar(20) not null,
  caption   varchar(20) not null,
  pc_name   varchar(20) not null,
  reg_num     int         not null,
  pc_year   int         not null,
      constraint in_FK
      foreign key (c_name) references Category,
      constraint in_FK
          foreign key (caption) references Submissions,
      constraint in_FK
          foreign key (reg_num) references Photographer,
      constraint in_FK
          foreign key (pc_name, pc_year) references PhotographyContest

);
create table Receives(
  dat_e     Date not null,
  name     varchar(20) not null,
  caption  varchar(20) not null,
  reg_num     int         not null,
  year     int         not null,
  constraint Receives_FK
      foreign key (caption) references Submissions,
  constraint Receives_FK
      foreign key (name, year) references PhotographyContest,
      constraint Receives_FK
          foreign key (reg_num) references Photographer

);

create table Submits(
  dat_e     Date not null,
  name     varchar(20) not null,
  caption  varchar(20) not null,
  reg_num   int         not null,
  year      int         not null,
  constraint Submits_FK
      foreign key (caption) references Submissions ,
  constraint Submits_FK
      foreign key (name, year) references PhotographyContest,
      constraint Submits_FK
          foreign key (reg_num) references Photographer

);

create table Publish(
  name     varchar(20) not null,
  caption  varchar(20) not null,
  reg_num   int         not null,
  year      int         not null,
  m_year    int         NOT null,
  constraint Publish_FK
      foreign key (caption) references Submissions,
  constraint Publish_FK
      foreign key (name, year) references PhotographyContest,
  constraint Publish_FK
      foreign key (reg_num) references Photographer,
  constraint Publish_FK
      foreign key (m_year) references Submissions

);

create table is1(
  name     varchar(20) not null,
  mem_id   int         not null,
  year      int         not null,
  constraint is_FK
      foreign key (name, year) references PhotographyContest,
  constraint is_FK
      foreign key (mem_id) references Members
);

create table of1(
  name     varchar(20) not null,
  mem_id   int         not null,
  year      int         not null,
  constraint of_FK
      foreign key (name, year) references PhotographyContest,
  constraint of_FK
      foreign key (mem_id) references Members
);

create table Nominate(
  score    int         not null,
  comment  varchar(20) not null,
  name     varchar(20) not null,
  caption  varchar(20) not null,
  reg_num   int         not null,
  year      int         not null,
  mem_id    int         NOT null,
  constraint Nominate_FK
      foreign key (caption) references Submissions,
  constraint Nominate_FK
      foreign key (name, year) references PhotographyContest,
  constraint Nominate_FK
      foreign key (reg_num) references Photographer,
  constraint Nominate_FK
      foreign key (mem_id) references Members

);

create table of2(
  award_name     varchar(20) not null,
  name    varchar(20)   not null,
  year      int         not null,
  constraint of_FK
      foreign key (name, year) references PhotographyContest,
  constraint of_FK
      foreign key (award_name) references Award
);

create table Win(
  award_name     varchar(20) not null,
  name     varchar(20) not null,
  caption  varchar(20) not null,
  reg_num   int         not null,
  year      int         not null,
  constraint Win_FK
      foreign key (caption) references Submissions,
  constraint Win_FK
      foreign key (name, year) references PhotographyContest,
  constraint Win_FK
      foreign key (reg_num) references Photographer,
  constraint Win_FK
      foreign key (award_name) references Award

);
