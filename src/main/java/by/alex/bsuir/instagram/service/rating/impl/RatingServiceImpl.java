package by.alex.bsuir.instagram.service.rating.impl;

import by.alex.bsuir.instagram.dao.rating.RatingDao;
import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.entity.Rating;
import by.alex.bsuir.instagram.util.enums.RatingTypeEnum;
import by.alex.bsuir.instagram.dto.RatingDTO;
import by.alex.bsuir.instagram.service.rating.RatingService;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("ratingService")
public class RatingServiceImpl implements RatingService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private RatingDao ratingDao;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public long saveRating(RatingDTO ratingDTO) {
        Rating rating = conversionService.convert(ratingDTO, Rating.class);
        long id = ratingDao.saveRating(rating);
        LOGGER.info(messageSource.getMessage("service.rating.save", new Object[]{ratingDTO},
                InstagramConstants.LOGGER_LOCALE));

        return id;
    }

    @Override
    @Transactional
    public void deleteRating(long id) {
        ratingDao.deleteRating(id);
        LOGGER.info(messageSource.getMessage("service.rating.delete", new Object[]{id},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional
    public void updateRating(RatingDTO ratingDTO) {
        Rating rating = conversionService.convert(ratingDTO, Rating.class);
        ratingDao.updateRating(rating);
        LOGGER.info(messageSource.getMessage("service.rating.update", new Object[]{ratingDTO},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional(readOnly = true)
    public RatingDTO getRatingById(long id) {
        RatingDTO ratingDTO = conversionService.convert(ratingDao.getRating(id), RatingDTO.class);
        LOGGER.info(messageSource.getMessage("service.rating.getRatingById", new Object[]{id, ratingDTO},
                InstagramConstants.LOGGER_LOCALE));

        return ratingDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RatingDTO> getListOfRatings() {
        List<Rating> ratings = ratingDao.getListOfRatings();
        List<RatingDTO> ratingDTOList = new ArrayList();

        for (Rating rating : ratings) {
            ratingDTOList.add(conversionService.convert(rating, RatingDTO.class));
        }
        LOGGER.info(messageSource.getMessage("service.rating.getList", new Object[]{ratingDTOList}, InstagramConstants.LOGGER_LOCALE));

        return ratingDTOList;
    }

    @Override
    @Transactional
    public synchronized PostDTO saveOrDeleteRating(PostDTO postDTO, RatingDTO ratingDTO) {
        Rating returnedRating = ratingDao.getRatingBySenderAndPostId(ratingDTO.getSender(), ratingDTO.getPost());
        if (returnedRating == null) {
            ratingDao.saveRating(conversionService.convert(ratingDTO,Rating.class));
            setPostRatings(postDTO);
        } else if (returnedRating.getType().equals(ratingDTO.getType().getType())){
            ratingDao.deleteRating(returnedRating.getRatingId());
            setPostRatings(postDTO);

        } else {
            returnedRating.setType(ratingDTO.getType().getType());
            ratingDao.updateRating(returnedRating);
            setPostRatings(postDTO);
        }

        return postDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public void setPostRatings(PostDTO postDTO) {
        postDTO.setLike(ratingDao.getRatingCount(postDTO.getId(),RatingTypeEnum.LIKE.getType()));
        postDTO.setDislike(ratingDao.getRatingCount(postDTO.getId(), RatingTypeEnum.DISLIKE.getType()));
    }
}
