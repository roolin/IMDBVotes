drop table rating;
drop table movie;

create table movie (
    id serial primary key,
    tconst text, -- alphanumeric unique identifier of the title
    title_type text, -- the type/format of the title (e.g. movie, short, tvseries, tvepisode, video, etc)
    primary_title text, -- the more popular title / the title used by the filmmakers on promotional materials at the point of release
    original_title text, -- original title, in the original language
    is_adult boolean, -- 0: non-adult title; 1: adult title
    start_year char(4), -- represents the release year of a title. In the case of TV Series, it is the series start year
    end_year char(4), -- TV Series end year. ‘\N’ for all other title types
    runtime_minutes text, -- primary runtime of the title, in minutes
    genres text[] -- includes up to three genres associated with the title
);

create table rating (
    id serial primary key,
    id_movie integer references movie(id),

    tconst text, -- alphanumeric unique identifier of the title
    average_rating numeric(3,1), -- weighted average of all the individual user ratings
    numVotes integer -- number of votes the title has received
);

create table title (
    id serial primary key,
    title_id text, -- a tconst, an alphanumeric unique identifier of the title
    ordering integer, -- a number to uniquely identify rows for a given titleId
    title text, -- the localized title
    region text, -- the region for this version of the title
    language text, -- the language of the title
    types text[], -- Enumerated set of attributes for this alternative title. One or more of the following: "alternative", "dvd", "festival", "tv", "video", "working", "original", "imdbDisplay". New values may be added in the future without warning
    attributes text[], -- Additional terms to describe this alternative title, not enumerated
    is_original_title boolean -- 0: not original title; 1: original title
);

create table votes (
    id serial primary key,
    id_movie integer references movie(id),
    rate int,
    votes int
);


create index idx_movie_tconst on movie(tconst);
create index idx_rating_tconst on rating(tconst);
create index idx_rating_id_movie on rating(id_movie);
create index idx_votes_id_movie on votes(id_movie);
update rating r set id_movie = m.id from movie m where m.tconst = r.tconst;

