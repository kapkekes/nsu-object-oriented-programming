from pathlib import Path
from random import shuffle
from typing import Iterator, TypedDict


class SampleConfiguration(TypedDict):
    upper_bound: int
    multiplier: int
    cut_size: int | None


SampleName = str


def iter_primes() -> Iterator[int]:
    """Simple iterator, which returns prime numbers in ascending order.

    Yields:
        A prime number.
    """
    d: dict[int, list[int]] = {}
    q: int = 2

    while True:
        if q not in d:
            yield q
            d[q * q] = [q]
        else:
            for p in d[q]:
                d.setdefault(p + q, []).append(p)
            del d[q]

        q += 1


def _get_prime_sample(upper_bound: int, multiplier: int, cut_size: int | None = None) -> list[int]:
    sample: list[int] = []
    for number in iter_primes():
        if number <= upper_bound:
            sample.append(number)
        else:
            break

    sample.reverse()

    if cut_size is not None:
        if len(sample) < cut_size:
            raise ValueError(f"{cut_size} is larger than the quantity of the primes less than {upper_bound}")

        sample = sample[:cut_size]

    sample *= multiplier
    shuffle(sample)

    return sample


def _stringify(ints: list[int]) -> str:
    return "\n".join(map(str, ints))


def main(samples: dict[SampleName, SampleConfiguration]) -> None:
    """The main routine, which creates samples as described in ``samples`` dict.

    Args:
        samples:
            The dict of configurations on how to generate samples.

    Returns:
        Nothing.
    """
    directory = Path(__file__).parent.parent / "resources"

    for file, kwargs in samples.items():
        path = directory / f"{file}.txt"
        path.write_text(
            _stringify(_get_prime_sample(**kwargs)),
            encoding="UTF-8"
        )


if __name__ == '__main__':
    main({
        "01-tiny": {
            "upper_bound": 1_000,
            "multiplier": 6,
            "cut_size": None,
        },
        "02-small": {
            "upper_bound": 10_000,
            "multiplier": 5,
            "cut_size": 300,
        },
        "03-medium": {
            "upper_bound": 100_000,
            "multiplier": 10,
            "cut_size": 1000,
        },
        "04-large": {
            "upper_bound": 1_000_000,
            "multiplier": 100,
            "cut_size": 1000,
        },
    })
