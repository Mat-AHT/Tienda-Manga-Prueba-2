package TiendaManga.Service;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private MangaRepository mangaRepository

}
